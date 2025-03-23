package SistemSwalayan;
import java.util.Scanner;

public class Swalayan {
    private static Scanner input = new Scanner(System.in);
    private static Pelanggan[] database = {
        new Pelanggan("Alice", "3812345678", "1234", 500000),
        new Pelanggan("Bob", "5612345678", "5678", 2000000),
        new Pelanggan("Charlie", "7412345678", "9999", 10000000)
    };

    public static Pelanggan autentikasi() {
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.print("Masukkan Nomor Pelanggan: ");
            String noPelanggan = input.nextLine();
            System.out.print("Masukkan PIN: ");
            String pin = input.nextLine();

            for (Pelanggan p : database) {
                if (p.getNoPelanggan().equals(noPelanggan)) {
                    if (p.autentikasi(pin)) {
                        System.out.println("\nLogin berhasil! Selamat datang, " + p.getNama());
                        p.tampilkanInfo();
                        return p;
                    }
                }
            }
            System.out.println("Nomor pelanggan atau PIN salah! Percobaan: " + (attempts + 1) + "/3");
        }
        return null;
    }
}