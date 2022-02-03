package �ַ���ƥ��;

public class RabinKarpRollingHash {
		//��s��Ѱ��t�����ص�һ���ҵ�������
		public static int rabinKarp(String s, String t) {
			if(s.length() < t.length()) {
				return -1;
			}
			if(t.length() == 0) {
				return 0;
			}
			long thash = 0, MOD = (long)1e9+7, B = 256;
			//char �����ܱ�ʾ��������256�����Խ��Ʒ����ʹ����256
			for(int i=0; i<t.length(); i++) {
				thash = (thash*B + t.charAt(i)) % MOD;
			}
			long hash = 0, P = 1;
			for(int i=0; i<t.length(); i++) {
				P = (P * B) % MOD;
			}
			
			for(int i=0; i<9; i++) {
				hash = (hash*B + s.charAt(i)) % MOD;
			}
			
			for(int i=0; i<s.length(); i++) {
				hash =(hash * B + s.charAt(i)) % MOD;
				if(hash == thash && s.substring(i-t.length()+1, i).equals(t)) {
					return i-t.length()+1;
				}
				hash = ((hash - (s.charAt(i-t.length()+1)*P)%MOD) + MOD)%MOD;
			}
			return -1;
		}	
}
