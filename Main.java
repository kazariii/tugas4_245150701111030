package SistemSwalayan;
import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== WELKAM TU MAI STOR =====");

        Pelanggan pelanggan = Swalayan.autentikasi();

        if (pelanggan == null) {
            System.out.println("Sesi berakhir. Silakan coba lagi nanti.");
            return;
        }

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Cek Saldo");
            System.out.println("2. Top Up Saldo");
            System.out.println("3. Lakukan Pembelian");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    pelanggan.tampilkanInfo();
                    break;
                case 2:
                    System.out.print("Masukkan jumlah top-up: Rp");
                    double jumlahTopUp = input.nextDouble();
                    input.nextLine();
                    System.out.print("Masukkan PIN: ");
                    String pinTopUp = input.nextLine();
                    pelanggan.topUp(jumlahTopUp, pinTopUp);
                    break;
                case 3:
                    System.out.print("Masukkan jumlah pembelian: Rp");
                    double jumlahPembelian = input.nextDouble();
                    input.nextLine();
                    System.out.print("Masukkan PIN: ");
                    String pinPembelian = input.nextLine();
                    pelanggan.pembelian(jumlahPembelian, pinPembelian);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan layanan kami!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
        }
    }
}
