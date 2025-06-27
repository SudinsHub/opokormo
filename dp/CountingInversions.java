public class CountingInversions {
    static int count = 0;
    void mergeTwoParts(int arr[], int low, int mid, int hi){
        int i = low, j= mid+1, k=0;
        int temp[] = new int[hi-low+1];
        while (i<=mid && j<=hi){
            if(arr[i] < arr[j]) temp[k++] = arr[i++];
            else {
                temp[k++] = arr[j++];
                count += (mid-i+1);
            }
        }
        while(i<=mid) temp[k++] = arr[i++];
        while(j<=hi) temp[k++] = arr[j++];
        for(int p=0; p<=hi-low; p++) arr[low+p]=temp[p];
    }
    
    void mergeSortRec(int arr[], int low, int hi){
        if(low<hi){
            int mid = (hi+low)/2;
            mergeSortRec(arr, low, mid);
            mergeSortRec(arr, mid+1, hi);
            mergeTwoParts(arr, low, mid, hi);
        }
    }
}
