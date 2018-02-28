import java.io.*;
import java.util.Scanner;

/*
*@author: pele
*@time: 2018/1/18 20:01
*@description:conway's life
*/
public class Conway {
    public static void main(String args[]){
        int row,column,count;//行数，列数，迭代次数
        String originalName,outputName;//初始文件名，输出文件名
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入行数：");
        row = Integer.parseInt(scanner.next());
        System.out.println("请输入列数");
        column = Integer.parseInt(scanner.next());
        System.out.println("请输入迭代次数");
        count = Integer.parseInt(scanner.next());
        System.out.println("请输入初始文件名");
        originalName = scanner.next();
        System.out.println("请输入输出文件名");
        outputName = scanner.next();
        scanner.close();

        int [][] state = new int[row][column];
        //读取初始文件,生成二维数组
        try {
            File directory = new File("");
            File originalFile = new File(directory.getAbsolutePath()+File.separator+originalName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(originalFile));
            for(int i=0;i<row;i++){
                String line = bufferedReader.readLine();
                line = line.replaceAll("\\s+","");
                for(int j=0;j<column;j++){
                    state[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //开始迭代
        for(int i=0;i<count;i++) {
            for(int j=0;j<row;j++){
                for(int k=0;k<column;k++){
                    int accord = 0;//记录周围存货个数
                    if(j-1>=0 && state[j-1][k]==1){
                        accord ++;
                    }
                    if(k-1 >=0 &&state[j][k-1] ==1){
                        accord ++;
                    }
                    if(k+1 <column && state[j][k+1] ==1){
                        accord ++;
                    }
                    if(j+1 <row && state[j+1][k]==1){
                        accord ++;
                    }
                    if(state[j][k] ==1){
                        if(accord<2) {
                            state[j][k] = 0;
                        }else if(accord >3){
                            state[j][k] = 0;
                        }
                    }else {
                        if(accord ==3){
                            state[j][k] = 1;
                        }
                    }

                }
            }
        }

        //写进输出文件
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(outputName)));
            for(int i=0;i<row;i++){
                for (int j=0;j<column;j++){
                    bufferedWriter.write(String.valueOf(state[i][j]));
                    bufferedWriter.write(" ");
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
