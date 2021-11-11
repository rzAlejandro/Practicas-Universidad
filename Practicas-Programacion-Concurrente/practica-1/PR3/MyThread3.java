import java.util.ArrayList;
import java.util.List;


public class MyThread3 extends Thread{
    private ArrayList<Integer> row;
    private Matriz m;
    private Matriz result;
    private int rIndex;

    public MyThread3(int i, ArrayList<Integer> row,Matriz m,Matriz r){
        rIndex=i;
        this.row=row;
        this.m=m;
        result=r;
    }

    public void run(){
        int n=row.size();
        ArrayList<Integer> rowResult=new ArrayList();
        for(int j=0;j<n;++j){
            int x=0;
            for(int k=0;k<n;++k){
                x=x+row.get(k)*m.getColumn(j).get(k);
            }
            rowResult.add(x);
        }
        result.setRow(rIndex, rowResult);
    }
}
