package com.mooc.house.thread;
import java.util.HashSet;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 锁定对象工具<br>
 * 可使现在指定的同一对象上同步执行，在不同对象上并发执行
 *
 * @param <T>
 */
public class SynchronizeObject<T> {

	private Logger log = LoggerFactory.getLogger(SynchronizeObject.class);

	/** 锁定对象集合用于记录锁定对象 */
	private final HashSet<Synchronize<T>> synchronizes = new HashSet<>();

	public SynchronizeObject() {
	}

	/**
	 * 是多线程在同一同步对象上运行为同步
	 *
	 * @param synchronize 同步对象
	 * @param run 运行方法
	 * @return 运行方法的返回值
	 */
	public Object run(T synchronize, Runnable run) {
		Object obj = null;
		try {
			while (true) {
				boolean flag = false;
				T synchronize2 = null;
				synchronized (synchronizes) {
					Synchronize<T> synObj = null;
					for (Synchronize<T> temp : synchronizes) {
						if (temp.object.equals(synchronize)) {
							synObj = temp;
							if (temp.isRun) {
								//此对象被持有，不能执行
								flag = true;
								synchronize2 = temp.object;
								temp.threadNumber++;
								break;
							}
						}
					}
					if (!flag) {
						//同步对象没被持有可运行
						if (synObj == null) {
							synObj = new Synchronize<T>();
							synObj.object = synchronize;
							synObj.threadNumber = 1;
							synObj.isRun = true;
							synchronizes.add(synObj);
						} else {
							synObj.isRun = true;
						}
						break;
					}
				}
				if (flag) {
					synchronized (synchronize2) {
						try {
							synchronize2.wait(1 * 1000);
						} catch (InterruptedException e) {
							log.warn(e.getMessage(), e);
						}
					}
				}
			}
			obj = run.run(null);
		} catch (Throwable t) {
			throw t;
		} finally {
			T synchronizeInv = null;
			synchronized (synchronizes) {
				Iterator<Synchronize<T>> ite = synchronizes.iterator();
				while (ite.hasNext()) {
					Synchronize<T> syn = ite.next();
					if (syn.object.equals(synchronize)) {
						synchronizeInv = syn.object;
						if (syn.threadNumber == 1) {
							ite.remove();
						} else {
							syn.threadNumber--;
							syn.isRun = false;
						}
						break;
					}
				}
			}
			synchronized (synchronizeInv) {
				synchronizeInv.notify();
			}
		}
		return obj;
	}

	/**
	 *
	 * 锁定类，用于控制某个对象上运行和等待
	 *
	 * @param <T2>
	 */
	private class Synchronize<T2> {
		/** 控制同步的对象 */
		public T2 object;
		/** 持有同步对象运行和在同步对象上等待的线程数 */
		public int threadNumber;
		/** 是否有线程持有同步对象运行 */
		public boolean isRun;
	}
}
