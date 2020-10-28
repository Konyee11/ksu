import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 畳み込み演算のプログラム
 */
public class Convolution{
    void run(String[] args) throws NumberFormatException, IOException {
        // 標準入力で信号x[n]とh[n]を取得
        System.out.println("x[n]を空白で区切って入力してください：");
        Double[] x = makeArray();
        System.out.println("h[n]を空白で区切って入力してください：");
        Double[] h = makeArray();

        //信号x[n]とh[n]を標準出力
        System.out.print("x[n] = ");
        System.out.println(Arrays.asList(x));
        System.out.print("h[n] = ");
        System.out.println(Arrays.asList(h));

        //畳み込み演算 y[n] = x[n] * h[n] の処理
        Double[] y = new Double[x.length + h.length - 1];  // 畳み込みにより得られる信号の長さは x[n]の長さ+h[n]の長さ-1
        Arrays.fill(y, 0.0);  // y[n]の各要素を0.0で初期化
        for(int n = 0; n < y.length; n++){
            for(int m = 0; m <= n; m++){
                if(m > x.length - 1){  // mが配列xのインデックス番号を超える時，x[m] = 0
                    y[n] += 0;
                }
                else if(n-m > h.length - 1){  // n-mが配列hのインデックス番号を超える時．h[n-m] = 0
                    y[n] += 0;
                }
                else{  // それ以外
                    y[n] += x[m] * h[n - m];
                }
            }
        }

        // 計算結果の出力
        System.out.print("x[n] * h[n] = ");
        System.out.println(Arrays.asList(y));

        // 結果をファイル出力
        PrintWriter pw = new PrintWriter("conv_res.txt");  // conv_res.txtに出力する．
        pw.print("x[n] = ");  // 信号x[n]の内容を出力
        pw.println(Arrays.asList(x));
        pw.print("h[n] = ");  // 信号h[n]の内容を出力
        pw.println(Arrays.asList(h));
        pw.print("x[n] * h[n] = ");  // 畳み込み演算によって得られた信号y[n]の内容を出力
        pw.println(Arrays.asList(y));
        pw.close();
    }
    // 標準入力をDouble型配列に変換する
    Double[] makeArray() throws IOException {
        // 標準入力から1行読み込む
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] strArr = line.split(" ");  // 読み込んだ1行分の文字列を空白文字で区切ってString型配列に変換

        // String型配列をDouble型配列に変換
        Double[] x = new Double[strArr.length];
        for(int i = 0; i < strArr.length; i++){
            x[i] = Double.parseDouble(strArr[i]);
        }
        return x;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        Convolution convolution = new Convolution();
        convolution.run(args);
    }
}