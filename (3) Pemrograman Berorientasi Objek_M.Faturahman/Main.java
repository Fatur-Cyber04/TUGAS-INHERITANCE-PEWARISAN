import java.util.ArrayList;
import java.util.Scanner;

class Mahasiswa {
    protected String nim;
    protected String nama;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }
    
    public String getNama() {
        return nama;
    }
}

class NilaiMahasiswa extends Mahasiswa {
    private double nilai;
    private String grade;
    private String status;

    public NilaiMahasiswa(String nim, String nama, double nilai) {
        super(nim, nama);
        this.nilai = nilai;
        hitungGradeDanStatus();
    }

    private void hitungGradeDanStatus() {
        if (nilai >= 80 && nilai <= 100) {
            grade = "A";
            status = "Lulus";
        } else if (nilai >= 70 && nilai <= 79) {
            grade = "B";
            status = "Lulus";
        } else if (nilai >= 60 && nilai <= 69) {
            grade = "C";
            status = "Lulus";
        } else if (nilai >= 50 && nilai <= 59) {
            grade = "D";
            status = "Tidak Lulus";
        } else if (nilai >= 0 && nilai < 50) {
            grade = "E";
            status = "Tidak Lulus";
        }
    }

    public String getNim() { return nim; }
    public double getNilai() { return nilai; }
    public String getGrade() { return grade; }
    public String getStatus() { return status; }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<NilaiMahasiswa> daftarMahasiswa = new ArrayList<>();

        System.out.println("=== PROGRAM ENTRY NILAI MAHASISWA ===");
        int jumlahData = 0;
        boolean jumlahValid = false;

        while (!jumlahValid) {
            System.out.print("Masukkan jumlah mahasiswa yang ingin dientri (Max 10): ");
            jumlahData = input.nextInt();
            input.nextLine(); 

            if (jumlahData > 0 && jumlahData <= 10) {
                jumlahValid = true;
            } else {
                System.out.println("Maaf, input salah! Jumlah mahasiswa minimal 1 dan maksimal 10.");
            }
        }

        for (int i = 0; i < jumlahData; i++) {
            System.out.println("\nData Mahasiswa ke-" + (i + 1));
            System.out.print("NIM: ");
            String nim = input.nextLine();
            System.out.print("Nama: ");
            String nama = input.nextLine();
            
            double nilai = -1;
            boolean nilaiValid = false;
            while (!nilaiValid) {
                System.out.print("Nilai: ");
                nilai = input.nextDouble();
                if (nilai >= 0 && nilai <= 100) {
                    nilaiValid = true;
                } else {
                    System.out.println("Input nilai anda salah. Silakan masukkan nilai antara 0-100.");
                }
            }
            input.nextLine(); 
            daftarMahasiswa.add(new NilaiMahasiswa(nim, nama, nilai));
        }

        System.out.println("\n=== HASIL OUTPUT ===");
        
        ArrayList<String> lulus = new ArrayList<>();
        ArrayList<String> tidakLulus = new ArrayList<>();
        ArrayList<String> gradeA = new ArrayList<>();
        ArrayList<String> gradeB = new ArrayList<>();
        ArrayList<String> gradeC = new ArrayList<>();
        ArrayList<String> gradeD = new ArrayList<>();
        ArrayList<String> gradeE = new ArrayList<>();
        double totalNilai = 0;
        StringBuilder deretNilai = new StringBuilder();

        
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            NilaiMahasiswa mhs = daftarMahasiswa.get(i);
            
            
            System.out.println("NIM   : " + mhs.getNim());
            System.out.println("Nama  : " + mhs.getNama());
            System.out.println("Nilai : " + (mhs.getNilai() == (int)mhs.getNilai() ? (int)mhs.getNilai() : mhs.getNilai()));
            System.out.println("Grade : " + mhs.getGrade());
            System.out.println("====================================================");

            
            totalNilai += mhs.getNilai();
            deretNilai.append(mhs.getNilai() == (int)mhs.getNilai() ? (int)mhs.getNilai() : mhs.getNilai());
            if (i < daftarMahasiswa.size() - 1) deretNilai.append("+");

            if (mhs.getStatus().equals("Lulus")) lulus.add(mhs.getNama());
            else tidakLulus.add(mhs.getNama());

            switch (mhs.getGrade()) {
                case "A": gradeA.add(mhs.getNama()); break;
                case "B": gradeB.add(mhs.getNama()); break;
                case "C": gradeC.add(mhs.getNama()); break;
                case "D": gradeD.add(mhs.getNama()); break;
                case "E": gradeE.add(mhs.getNama()); break;
            }
        }

        
        System.out.println("Jumlah Mahasiswa : " + daftarMahasiswa.size());
        System.out.println("Jumlah Mahasiswa yg Lulus : " + lulus.size() + " yaitu " + String.join(", ", lulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tidakLulus.size() + " yaitu " + String.join(", ", tidakLulus));
        
        if (!gradeA.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA.size() + " yaitu " + String.join(", ", gradeA));
        if (!gradeB.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB.size() + " yaitu " + String.join(", ", gradeB));
        if (!gradeC.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai C = " + gradeC.size() + " yaitu " + String.join(", ", gradeC));
        if (!gradeD.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD.size() + " yaitu " + String.join(", ", gradeD));
        if (!gradeE.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai E = " + gradeE.size() + " yaitu " + String.join(", ", gradeE));

        double rataRata = totalNilai / daftarMahasiswa.size();
        System.out.println("Rata-rata nilai mahasiswa adalah : " + deretNilai.toString() + " / " + daftarMahasiswa.size() + " = " + rataRata);
        
        input.close();
    }
}