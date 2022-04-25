import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import GraphicsStuff.m_Frame;
import K_means.KMeans;

public class Main {

	public static void main(String[] args) throws IOException {
//		new m_Frame();
		int numPartitions=10;
		File file=new File("src/GraphicsStuff/img1.png");
		IMG mainIMG=new IMG(file);
		
		int colorVectors[][]=mainIMG.getColorVectors();
		int size=mainIMG.getSize();
		
//		for (int i=0;i<2500;i++) {
////			System.out.print("a: "); 
////			System.out.print(colorVectors[i][0]);
////			System.out.print(" r: "); 
////			System.out.print(colorVectors[i][1]);
////			System.out.print(" g: "); 
////			System.out.print(colorVectors[i][2]);
////			System.out.print(" b: "); 
////			System.out.println(colorVectors[i][3]);
//		}
		
		//variables to collect results
		int [][] representatives = new int [numPartitions][4];
		int[] map=new int[size];
		double minJClust=-1;
		
		//we run the algorithm 10 times
		for (int i=0;i<15;i++) {
			KMeans algorithm=new KMeans(colorVectors,4, size,numPartitions);
			algorithm.init();
			while (!algorithm.iterate(0.0005)) {}
			//end of algorithm
			
			if (minJClust==-1 || minJClust>algorithm.getJ_Clust()) {
				representatives=algorithm.getRepresentatives();
				map=algorithm.getMap();
				minJClust=algorithm.getJ_Clust();
			}
			System.out.println(i+1);
		}
		
		
		int[][] rgbaVectors=convertData(representatives, map,size);
		mainIMG.getClusteringResults(mainIMG.rgbaToPixels(rgbaVectors, size));
	}

	public static int[][] convertData (int[][] reps, int[] map, int size) {
		int [][] rgbaVectors=new int [size][4];
		for (int i=0;i<size;i++) {
			rgbaVectors[i]=reps[map[i]];
		}
		
		return rgbaVectors;
	}
	
}
