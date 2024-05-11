package com.quyduoi.lab2_md18309_ph41939

fun main () {
    // Khai báo và sử dụng kỹ thuật Null safety
    println("Nhập mã số sinh viên: ")
    val maSinhVien : String? = readLine();

//    var tenSinhVien : String = null

    val tenSv = getTenSv(maSinhVien!!)
    println("Tên sinh viên với mã $maSinhVien là: $tenSv")
}

val danhSachSV : Map<String , String> = mutableMapOf("PH41939" to "Nguyễn Sỹ Quý", "PH12345" to "Nguyễn Văn A", "PH12346" to "Nguyễn Văn B")

fun getTenSv (maSinhVien : String) : String? {
    val tenSv = danhSachSV.get(maSinhVien)
    return tenSv
}