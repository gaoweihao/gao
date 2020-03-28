package arithmetic;


public class Arithmetic1 {
	public static void main(String[] args){
        System.out.println(commonDivisor(90,50));
    }

    /**
     * 题目：一个数如果恰好等于它的因子之和，这个数就称为"完数"。例如6=1＋2＋3。编程找出1000以内的所有完数。
     * */
    public void  wanshu(Integer num , Integer sum){
        if(sum ==0){
            sum = 1 ;
        }
        for(int i = 2 ; i < num ; i ++){
            if( num % i == 0){
                sum+=i;
                wanshu(num / i , sum);
            }
        }
    }

    /**
     * 求s = a + aa + aaa + aaaa + aa...a的值，
     * 其中a是一个数字。
     * 例如2 + 22 + 222 + 2222 + 22222(此时共有5个数相
     * */
    public static Integer getNum(Integer num ,Integer n){
        int s = 0 , t = 0;
        for(int i = 1 ; i <= n ; i++ ){
            t += num;
            num = num * 10;
            s+= t;
        }
        return 1;
    }


    /**
     * 求两个数的最大公约数
     * 最小公倍速 x*y/commonDivisor(x,y)
     * */

    public static int commonDivisor(int x,int y){
        if(x<y){
            int t=x;
            x=y;
            y=t;
        }
        while(y!=0){
            if(x==y)return x;
            else{
                int k=x%y;
                System.out.println("y :"+y);
                System.out.println("k :"+k);
                x=y;
                y=k;
            }
        }
        return x;
    
}

    /**
     * 对一个数分解因数
     * */
    public static void fenjie(int n) {
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i);
                if(n!=i){
                    System.out.print("*");
                }
                fenjie(n/i);
            }
        }
        System.exit(0); //退出程序
    }

	/**
	 * 判断101-200之间有多少个素数，
	 * 并输出所有素数
	 * 判断素数的方法：
	 * 用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。
	 * *//*
	public static boolean sushu(int num){
		boolean flag=true;
		if(num== 2||num==1){
			return flag;
		}else{
			for(int i =2;i<num/2;i++){
				if(num % i ==0){
					flag=false;
				}
			}
			return flag;
		}
	}

	*//**
	 * 古典问题：
	 * 有一对兔子，
	 * 从出生后第3个月起每个月都生一对兔子，
	 * 小兔子长到第四个月后每个月又生一对兔子，
	 * 假如兔子都不死，
	 * 问每个月的兔子总数为多少？
	 * 分析
	 * 1,1,2,3,5,8,13,21.... 
	 * 1 2 3 4 5 6 7  8 ......
	 * * *//*
	public int  getNum(int moth){
		
		if(moth ==1 || moth ==2){
			return 1;
		}else{
			return getNum(moth-1)+getNum(moth-2);
		}
	}*/
	
}
