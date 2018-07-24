import neoonki.util.DateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long lastRuntimeEnd = 20180721133254943L;
		Long interval = 1000L;
		Long now = DateUtil.getCurrentLongDate();
		boolean isRuntime = false;
		System.out.println(now);//2018/07/20 13:32:54.943
		if(now > lastRuntimeEnd + interval) {
			isRuntime = true;
		}
		System.out.println(isRuntime);
	}

}
