package Section4.Sorting;

public class MergedSort {

    public static void main(String[] args){
       int[] nums1 = new int[] {1,2,3};
        int m = 6;
        int[] nums2 = new int[] {2,5,6};
       int  n = 3;

       merge(nums1,m,nums2,n);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int [] newArray = new int[m];
        int mCount = 0;
        int nCount = 0;
        int i = 0;
        while (i < m) {


                if (nums1[mCount] <= nums2[nCount]) {
                    newArray[i] = nums1[mCount];
                    mCount++;
                    i++;
                } else {
                    newArray[i] = nums2[nCount];
                    nCount++;
                    i++;
                }

                System.out.println(newArray[i]);
            if ( mCount <=m && nums1[mCount] == 0){
                mCount++;
            }
            if(nCount <=n && nums2[nCount] == 0 ) {
                nCount++;
            }


        }


    }

}
