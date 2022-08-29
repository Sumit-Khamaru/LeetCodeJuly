import java.util.ArrayList;
import java.util.List;

public class MyCalendarI {
    public static void main(String[] args) {
        MyCalendar obj = new MyCalendar();
        System.out.println(obj.book(10, 20));
        System.out.println(obj.book(15, 25));
        System.out.println(obj.book(20, 30));
    }

}
// we just need to check the boundaries of the booking
class MyCalendar {
//    We need to return a list of array
    List<int[]> list;
    public MyCalendar() {
    list = new ArrayList<>();
    }
    public boolean book(int start, int end) {
//     we traverse the list and check if the booking is possible
        for(int[] a : list) {
            if(a[0] < end && start < a[1]) {
                return false;
            }
        }
        list.add(new int[] {start, end});
        return true;
    }
}
