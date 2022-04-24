package sparsearray;

/**
 * @author WuW
 * @date 2021/12/30 10:17
 * @Des 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        // 1表示黑子 2表示白子 0表示无子
        array[1][2] = 1;
        array[2][3] = 2;

        // 打印原数组
        System.out.println("==================原数组==================");
        for(int[] arr : array){
            for(int ele : arr){
                System.out.printf("%d\t", ele);
            }
            System.out.println();
        }

        // 将原数组转换成稀疏数组
        int sum = 0;
        for(int[] arr : array){
            for(int ele : arr){
                if(ele != 0){
                    sum++;
                }
            }
        }

        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int count = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                    count++;
                }
            }
        }

        // 输出稀疏数组
        System.out.println("==================稀疏数组==================");
        for(int[] arr : sparseArr){
            for(int ele : arr){
                System.out.printf("%d\t", ele);
            }
            System.out.println();
        }

        // 还原数组
        int[][] oArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 0; i < sparseArr[0][2]; i++) {
            oArr[sparseArr[i+1][0]][sparseArr[i+1][1]] = sparseArr[i+1][2];
        }

        // 还原后数组打印
        System.out.println("===================还原后数组=====================");
        for(int[] arr : oArr){
            for(int ele : arr){
                System.out.printf("%d\t", ele);
            }
            System.out.println();
        }


    }
}
