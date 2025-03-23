package SistemSwalayan;

public class Pelanggan {
    private String nama;
    private String noPelanggan;
    private String pin;
    private double saldo;
    private String jenisRekening;
    private int percobaanGagal;
    private boolean akunDiblokir;

    public Pelanggan(String nama, String noPelanggan, String pin, double saldo) {
        if (noPelanggan.length() != 10) {
            throw new IllegalArgumentException("Nomor pelanggan harus 10 digit!");
        }
        this.nama = nama;
        this.noPelanggan = noPelanggan;
        this.pin = pin;
        this.saldo = saldo;
        this.jenisRekening = tentukanJenisRekening();
        this.percobaanGagal = 0;
        this.akunDiblokir = false;
    }

    private String tentukanJenisRekening() {
        String kode = noPelanggan.substring(0, 2);
        switch (kode) {
            case "38": return "Silver";
            case "56": return "Gold";
            case "74": return "Platinum";
            default: return "Tidak Valid";
        }
    }

    public String getNoPelanggan() {
        return noPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public double getSaldo() {
        return saldo;
    }

    protected boolean autentikasi(String pinInput) {
        if (akunDiblokir) {
            System.out.println("Akun telah diblokir! Transaksi tidak bisa dilakukan.");
            return false;
        }

        if (this.pin.equals(pinInput)) {
            percobaanGagal = 0;
            return true;
        } else {
            percobaanGagal++;
            System.out.println("PIN salah! Percobaan ke-" + percobaanGagal);

            if (percobaanGagal >= 3) {
                akunDiblokir = true;
                System.out.println("Akun telah diblokir karena 3x kesalahan PIN.");
            }
            return false;
        }
    }

    public boolean isSaldoCukup(double jumlah) {
        return (saldo - jumlah) >= 10000;
    }

    public void topUp(double jumlah, String pinInput) {
        if (!autentikasi(pinInput)) return;

        if (jumlah <= 0) {
            System.out.println("Top Up gagal! Jumlah harus lebih dari 0.");
            return;
        }

        saldo += jumlah;
        System.out.println("Top Up berhasil! Saldo Anda sekarang: Rp" + saldo);
    }

    public void pembelian(double jumlah, String pinInput) {
        if (!autentikasi(pinInput)) return;

        if (!isSaldoCukup(jumlah)) {
            System.out.println("Transaksi gagal! Saldo tidak mencukupi.");
            return;
        }

        double cashback = hitungCashback(jumlah);
        saldo -= jumlah;
        saldo += cashback;

        System.out.println("Pembelian berhasil sebesar Rp" + jumlah);
        System.out.println("Cashback: Rp" + cashback);
        System.out.println("Saldo tersisa: Rp" + saldo);
    }

    private double hitungCashback(double jumlah) {
        if (jumlah < 1000000) {
            if (jenisRekening.equals("Gold")) return 0.02 * jumlah;
            if (jenisRekening.equals("Platinum")) return 0.05 * jumlah;
        } else {
            if (jenisRekening.equals("Silver")) return 0.05 * jumlah;
            if (jenisRekening.equals("Gold")) return 0.07 * jumlah;
            if (jenisRekening.equals("Platinum")) return 0.1 * jumlah;
        }
        return 0;
    }

    public void tampilkanInfo() {
        System.out.println("\nInformasi Pelanggan:");
        System.out.println("Nama         : " + nama);
        System.out.println("Nomor        : " + noPelanggan);
        System.out.println("Jenis Rekening: " + jenisRekening);
        System.out.println("Saldo        : Rp" + saldo);
        System.out.println("Status Akun  : " + (akunDiblokir ? "DIBLOKIR" : "AKTIF"));
    }
}
