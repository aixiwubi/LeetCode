public class ZigZag {
    public String convert(String s, int numRows) {
        if(numRows<2){
            return s;
        }
        System.out.println("hi");
        int currentPosition;
        int fixleft = numRows*2-2 ;
        int fixright = 0;
        boolean isLeft = true;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< numRows; i++){
            currentPosition = i;
            int left = fixleft-2*i ;
            int right = fixright+2*i;
            //System.out.println(currentPosition);
            System.out.println(left);
            System.out.println(right);
            while(currentPosition<s.length()){
                System.out.println("current Position is " + currentPosition );
                System.out.println(s.charAt(currentPosition));

                if(i == 0){
                   //left
                 //  System.out.println(s.charAt(currentPosition));
                   sb.append(s.charAt(currentPosition));
                   currentPosition+=left;
               }
               else if(i == numRows-1){
                   //right
               //    System.out.println(s.charAt(currentPosition));
                   sb.append(s.charAt(currentPosition));
                   currentPosition+=right;

               }
               else{
                   if(isLeft){
                  //     System.out.println(s.charAt(currentPosition));
                       sb.append(s.charAt(currentPosition));
                       isLeft = false;
                       currentPosition+=left;
                   }
                   else{
                   //    System.out.println(s.charAt(currentPosition));
                       sb.append(s.charAt(currentPosition));
                       isLeft = true;
                       currentPosition+=right;
                   }
                   //left then right
               }
            }
            isLeft = true;
            System.out.println("-------------------");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    public static void main(String [] args){

        ZigZag a = new ZigZag();
        a.convert("abcde",4);
    }

}
