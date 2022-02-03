package �ַ���ƥ��;

public class KMP {

	public static int[] getNext(String ps) {
		char[] p = ps.toCharArray();
		int[] next = new int[p.length];
		next[0] = -1;
		int j = 0;
		int k = -1;
		while (j < p.length - 1) {
			if (k == -1 || p[j] == p[k]) {
				next[++j] = ++k;
			} else {
				k = next[k];
			}
		}
		return next;
	}

	public static int kmp(String ts, String ps) {
		char[] t = ts.toCharArray();
		char[] p = ps.toCharArray();
		int i = 0; // ������λ��
		int j = 0; // ģʽ����λ��
		int[] next = getNext(ps);
		while (i < t.length && j < p.length) {
			if (j == -1 || t[i] == p[j]) { // ��jΪ-1ʱ��Ҫ�ƶ�����i����ȻjҲҪ��0
				i++;
				j++;
			} else {
				// i����Ҫ������
				// i = i - j + 1;
				j = next[j]; // j�ص�ָ��λ��
			}
		}
		if (j == p.length) {
			return i - j;
		} else {
			return -1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(kmp("String", "ing"));
	}
	
}
