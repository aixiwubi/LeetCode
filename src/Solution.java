import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
public class Solution {
    //permutation
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> current = new LinkedList<>();
        for(int num : nums){
            current.add(num);
        }
        for(int i = 0; i<current.size();i++){
            LinkedList<Integer> passOn = new LinkedList<Integer>();
            passOn.addAll(current);
            passOn.remove(i);
            for(LinkedList<Integer> a : getPermutation(passOn)){
                a.add(current.get(i));
                result.add(a);
            }
        }

        return result;
    }
    private LinkedList<LinkedList<Integer>> getPermutation(LinkedList<Integer> nums){
        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        if(nums.size()<=1){
            result.add(nums);
            return result;
        }
        for(int i = 0; i< nums.size(); i++){
            LinkedList<Integer> passOn = new LinkedList<Integer>();
            passOn.addAll(nums);
            passOn.remove(i);
            for(LinkedList<Integer> a : getPermutation(passOn)){
                a.add(nums.get(i));
                result.add(a);
            }

        }
        return result;
    }
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> check = new HashSet<>();
        for(int num : nums){
            if(!check.add(num)){
                result.add(num);
            }
        }
        return result;
    }
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int result = 0;
        if(timeSeries.length == 0){
            return 0;
        }
        if(timeSeries.length==1){
            return 2;
        }
        int begin = timeSeries[0];

        for(int i = 1; i < timeSeries.length; i++){
           if(timeSeries[i]-begin<duration){
               result+=timeSeries[i]-begin;
           }
           else{
               result+= duration;
           }
           begin = timeSeries[i];
        }
        return result+duration;
    }
    //spiral order
    public List<Integer> spiralOrder(int[][] matrix) {

        //arraylist to store result
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(matrix.length ==0){
            return arrayList;
        }
        //starting row and col max min
        int rowMin = 0;
        int colMin = 0;
        int rowMax = matrix.length-1;
        int colMax = matrix[0].length-1;
        //direction index 0 -> right, 1 -> down, 2 ->left, 3->up, 5 -> end of 2d array
        int direct = 0;
        //current Row and Column indexs
        int curR = 0;
        int curC = 0;

        while(direct!=5){
            //start to scan right
            if(direct==0){
                //row index stay the same move column index to the right/increase
                for(int i = curC; i <= colMax; i++){
                    //add current position
                    arrayList.add(matrix[curR][i]);
                    //update current column postion
                    curC = i;
                }
                //end of row , so the top part of the matrix is scaned, update the min row index (+1)
                rowMin +=1;
                //move down is followed by move right -> so increase the current row index
                curR += 1;
                // change direction to down
                direct =1;
                //check if reach the end of line
                if(curR>rowMax){
                    direct =5;
                }
            }
            //start scan down
            else if(direct==1){
                //column index remain same, increase row index
                for(int i = curR; i <= rowMax; i++){
                    arrayList.add(matrix[i][curC]);
                    curR = i;
                }
                //right edge of matrix is finished
                colMax -=1;
                //update current column postion
                curC -= 1;
                //update direction to left
                direct = 2;
                //check if out of range
                if(curC<colMin){
                    direct =5;
                }
            }
            else if(direct==2){
                for(int i = curC; i >= colMin; i--){
                    arrayList.add(matrix[curR][i]);
                    curC = i;
                }
                rowMax -=1;
                curR -= 1;
                direct = 3;
                if(curR<rowMin){
                    direct =5;
                }
            }
            else if(direct==3){
                for(int i = curR; i >= rowMin; i--){
                    arrayList.add(matrix[i][curC]);
                    curR = i;
                }
                colMin +=1;
                curC += 1;
                direct = 0;
                if(curC>colMax){
                    direct =5;
                }
            }
        }

        return arrayList;

    }
    public int singleNonDuplicate(int[] nums) {
       int start = 0;
       int end = nums.length-1;
       while(start < end){
           if(nums[start]+nums[end]!= nums[start+1]+nums[end-1]){
                if(nums[start+1]!=nums[start]){
                    if(nums[start+2]==nums[start+1]){
                        return nums[start];
                    }else{
                        return nums[start+1];
                    }
                }
                else{
                    if(nums[end-1]==nums[end-2]){
                        return nums[end];
                    }else{
                        return nums[end-1];
                    }

                }
           }
           start+=2;
           end-=2;
       }
       return nums[nums.length/2];
    }
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed.length==1){
            if(n==1&&flowerbed[0]==0){
                return true;
            }
            if(n==0){
                return true;
            }
            return false;
        }
        for(int i = 0; i < flowerbed.length; i++){
            if(n == 0){
                return true;
            }
            if(i==0){
                if(flowerbed[i+1]==0&&flowerbed[i]==0){
                    n-=1;
                    flowerbed[i]=1;

                }
            }
            else if(i == flowerbed.length-1){
                if(flowerbed[i-1]==0&&flowerbed[i]==0){
                    n-=1;
                    flowerbed[i]=1;

                }
            }
            else if(flowerbed[i-1]==0&&flowerbed[i]==0&&flowerbed[i+1]==0){
                n-=1;
                flowerbed[i]=1;

            }
        }
        if(n == 0){
            return true;
        }
        return false;
    }
    public List<String> fizzBuzz(int n) {
        ArrayList<String> result = new ArrayList<>();
        for(int i=1; i<=n; i++){
           if(i%3==0&&i%5==0){
                result.add("FizzBuzz");
           }
           else if(i%3==0){
                result.add("Fizz");
           }
           else if(i%5==0){
                result.add("Buzz");
           }
           else{
               result.add(Integer.toString(i));
           }
        }
        return result;
    }
    public static void main(String [] args){
        Solution s = new Solution();
        System.out.println(s.fizzBuzz(15));
    }
}
