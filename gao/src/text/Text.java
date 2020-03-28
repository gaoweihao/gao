package text;

public class Text {
	public static void main(String[] args) {
		int[] menkey = new int[15];
		int j =1,i=0,sum =0;
		
		while(sum<=15){
			if(menkey[i]!=1){
				j++;
			}
			if(j==7){
				menkey[i]=1;
				sum+=1;
				j=0;
			}
			System.out.println("menkey["+i+"]="+menkey[i]+"	i="+i+"	j="+j);
			if(i==14){
				i=-1;
			}
			i++;
			/*if(sum==15){
				break;			
			}*/
		}	
	}
}
