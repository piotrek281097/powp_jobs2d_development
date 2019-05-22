package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;
import edu.kis.powp.jobs2d.resources.ResourceClassSingleton;
import edu.kis.powp.jobs2d.resources.ResourcesClass;

/**
 * Plotter test.
 * 
 * @author Dominik
 */
public class Job2dDriverTest {
	private static Job2dDriver driver = new StubDriver();

	/**
	 * Driver test.
	 */
	public static void main(String[] args) {
		ResourceClassSingleton resourceClassSingleton = ResourceClassSingleton.getInstance();
		FiguresJoe.figureScript1(driver);
//		ResourcesClass resourcesClass = new ResourcesClass(1000, 1000);
//		resourcesClass.decrementInk(0, 0, 1, 1);
//		resourcesClass.decrementUsage(0, 0, 1, 1);
//		System.out.println(resourcesClass.getInk());
//		System.out.println(resourcesClass.getUsage());

	}

	private static class StubDriver implements Job2dDriver {

		@Override
		public void operateTo(int x, int y) {
			ResourceClassSingleton.getInstance().setInk(40);
			System.out.println("Driver operateTo action...");
		}

		@Override
		public void setPosition(int x, int y) {
			System.out.println("Driver setPosition action...");
		}
	};
}
