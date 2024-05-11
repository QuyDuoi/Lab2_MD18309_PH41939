import java.util.Scanner

class SinhVienModel(
    var tenSV: String,
    var mssv: String,
    var diemTB: Float,
    var daTotNghiep: Boolean?,
    var tuoi: Int?
)

class QuanLySinhVien {
    private val danhSachSinhVien = mutableMapOf<String, SinhVienModel>()

    fun themSinhVien(sv: SinhVienModel) {
        danhSachSinhVien[sv.mssv] = sv
    }

    fun suaSinhVien(mssv: String, sv: SinhVienModel) {
        if (danhSachSinhVien.containsKey(mssv)) {
            danhSachSinhVien[mssv] = sv
        } else {
            println("Không tìm thấy sinh viên có MSSV là $mssv.")
        }
    }

    fun xoaSinhVien(mssv: String) {
        if (danhSachSinhVien.containsKey(mssv)) {
            danhSachSinhVien.remove(mssv)
            println("Đã xóa sinh viên có MSSV là $mssv.")
        } else {
            println("Không tìm thấy sinh viên có MSSV là $mssv.")
        }
    }

    fun xemDanhSachSinhVien(): Map<String, SinhVienModel> {
        return danhSachSinhVien.toMap()
    }
}

fun String?.toSafeBooleanOrNull(): Boolean? {
    return if (this == null) null
    else if (this.equals("true", ignoreCase = true)) true
    else if (this.equals("false", ignoreCase = true)) false
    else null
}

fun main() {
    val quanLy = QuanLySinhVien()
    val scanner = Scanner(System.`in`)
    var tiepTuc: Boolean

    do {
        println("\nChọn chức năng:")
        println("1. Thêm sinh viên")
        println("2. Sửa thông tin sinh viên")
        println("3. Xóa sinh viên")
        println("4. Xem danh sách sinh viên")
        println("5. Thoát chương trình")
        print("Vui lòng chọn: ")
        val luaChon = scanner.nextInt()

        when (luaChon) {
            1 -> {
                var tenSV: String
                var mssv: String
                var diemTB: Float

                do {
                    println("\nNhập thông tin sinh viên mới:")
                    print("Tên sinh viên: ")
                    tenSV = readLine()?.trim() ?: ""
                    if (tenSV.isEmpty()) {
                        println("Vui lòng nhập tên sinh viên.")
                    }
                } while (tenSV.isEmpty())

                do {
                    print("MSSV: ")
                    mssv = readLine()?.trim() ?: ""
                    if (mssv.isEmpty()) {
                        println("Vui lòng nhập MSSV.")
                    }
                } while (mssv.isEmpty())

                do {
                    print("Điểm TB: ")
                    diemTB = readLine()?.toFloatOrNull() ?: Float.MIN_VALUE
                    if (diemTB == Float.MIN_VALUE) {
                        println("Vui lòng nhập điểm TB hợp lệ.")
                    }
                } while (diemTB == Float.MIN_VALUE)

                print("Đã tốt nghiệp (True/False): ")
                val daTotNghiepInput = readLine()?.takeIf { it.isNotBlank() } ?: null
                val daTotNghiep = daTotNghiepInput?.toBoolean()
                print("Tuổi: ")
                val tuoiInput = readLine()?.takeIf { it.isNotBlank() } ?: null
                val tuoi = tuoiInput?.toInt()

                val svMoi = SinhVienModel(tenSV, mssv, diemTB, daTotNghiep, tuoi)
                quanLy.themSinhVien(svMoi)
            }
            2 -> {
                println("\nNhập MSSV của sinh viên cần sửa:")
                print("MSSV: ")
                val mssv = readLine() ?: ""
                val danhSachSinhVien = quanLy.xemDanhSachSinhVien()
                if (danhSachSinhVien.containsKey(mssv)) {
                    println("\nNhập thông tin mới cho sinh viên:")
                    val svCu = danhSachSinhVien[mssv]!!
                    print("Tên sinh viên [${svCu.tenSV}]: ")
                    val tenSV = readLine()?.takeIf { it.isNotBlank() } ?: svCu.tenSV
                    print("Điểm TB [${svCu.diemTB}]: ")
                    val diemTBInput = readLine()?.takeIf { it.isNotBlank() } ?: svCu.diemTB.toString()
                    val diemTB = diemTBInput.toFloatOrNull() ?: svCu.diemTB
                    print("Đã tốt nghiệp (${svCu.daTotNghiep ?: "Không có thông tin"}) [true/false]: ")
                    val daTotNghiepInput = readLine()?.takeIf { it.isNotBlank() } ?: svCu.daTotNghiep.toString()
                    val daTotNghiep = daTotNghiepInput.toSafeBooleanOrNull() ?: svCu.daTotNghiep
                    print("Tuổi (${svCu.tuoi ?: "Không có thông tin"}) : ")
                    val tuoiInput = readLine()?.takeIf { it.isNotBlank() } ?: svCu.tuoi.toString()
                    val tuoi = tuoiInput.toIntOrNull() ?: svCu.tuoi

                    val svMoi = SinhVienModel(tenSV, mssv, diemTB, daTotNghiep, tuoi)
                    quanLy.suaSinhVien(mssv, svMoi)
                } else {
                    println("Không tìm thấy sinh viên có MSSV là $mssv.")
                }
            }
            3 -> {
                println("\nNhập MSSV của sinh viên cần xóa:")
                print("MSSV: ")
                val mssv = readLine() ?: ""
                quanLy.xoaSinhVien(mssv)
            }
            4 -> {
                val danhSachSinhVien = quanLy.xemDanhSachSinhVien()
                if (danhSachSinhVien.isEmpty()) {
                    println("Danh sách sinh viên trống.")
                } else {
                    println("Danh sách sinh viên:")
                    for ((mssv, sv) in danhSachSinhVien) {
                        println("MSSV: $mssv - Tên: ${sv.tenSV}, Điểm TB: ${sv.diemTB}, " +
                                "Tốt nghiệp: ${sv.daTotNghiep ?: "Không có thông tin"}, " +
                                "Tuổi: ${sv.tuoi ?: "Không có thông tin"}")
                    }
                }
            }
            5 -> {
                println("Kết thúc chương trình.")
                tiepTuc = false
            }
            else -> {
                println("Lựa chọn không hợp lệ.")
            }
        }

        println("\nTiếp tục chương trình? (Y/N): ")
        val tiepTucLuaChon = readLine() ?: ""
        tiepTuc = tiepTucLuaChon.equals("Y", ignoreCase = true)
    } while (tiepTuc)
}
