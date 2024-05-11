package com.quyduoi.lab2_md18309_ph41939

class SinhVienModel (var tenSV : String, var maSv : String, var diemTB : Float) {
    var daTotNghiep : Boolean? = null
    var tuoi : Int? = null
    fun getThongTin() : String {
        return "Tên sinh viên: $tenSV, Mã sinh viên: $maSv, Điểm trung bình: $diemTB, Tình trạng: $daTotNghiep, Tuổi: $tuoi"
    }
}