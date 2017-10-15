import java.io.*;
import java.util.*;

public class MaxValuePath {
    static int n, m;
    static double bestsum;
    static double[][] mat;
    static boolean[] used;
    static void best(int i, double sum) {
        if (i == n) {
            if (sum > best)
                bestsum = sum;
            return;
        }
        for (int j = 0; j < m; j++)
            if (!used[j]) {
                used[j] = true;
                best(i+1, sum+mat[i][j]);
                used[j] = false;
            }
    }
    static void error(String msg) {
        System.out.println(msg);
        System.exit(0);
    }
    public static void main(String[] argv) {
        int i, j, imax = 0, jmax = 0;
        boolean transpose = false;
        BufferedReader in = null;
        String str = "", line = "";
        StringTokenizer tokenizer;
        if (argv.length != 3)
            error("usage: java MaxValuePath <n> <m> <filename>");
        try {
            imax = n = Integer.parseInt(argv[0]);
            jmax = m = Integer.parseInt(argv[1]);
            if (n > m) {
                n -= m;
                m += n;
                n = m-n;
                transpose = true;
            }
        } catch (NumberFormatException e) { error("error: integers
required"); }
        try {
            in = new BufferedReader(new FileReader(argv[2]));
        } catch (IOException e) { error("error: missing file"); }
        try {
            while ((line = in.readLine()) != null)
                str += line + " ";
        } catch (IOException e) { error("bad file"); }
        tokenizer = new StringTokenizer(str, " \t\n\r\f");
        if (tokenizer.countTokens() != n*m)
            error("" + n*m + " values required; only have " +
tokenizer.countTokens());
        mat = new double[n][m];
        used = new boolean[m];
        try {
            for (i = 0; i < imax; i++) 
                for (j = 0; j < jmax; j++) 
                    if (transpose)
                        mat[j][i] =
Double.parseDouble(tokenizer.nextToken());
                    else
                        mat[i][j] =
Double.parseDouble(tokenizer.nextToken());
        } catch (Exception e) { error("bad number"); }
        for (i = 0; i < m; i++)
            used[i] = false;
        bestsum = Double.MIN_VALUE;
        best(0, 0.0);
        System.out.println("best sum = " + bestsum);
    }
}
