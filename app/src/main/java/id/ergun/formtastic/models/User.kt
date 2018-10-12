package id.ergun.formtastic.models

data class User(
        val namaDepan: String,
        val namaBelakang: String,
        val email: String,
        val jenisKelamin: String,
        val password: String,
        val cpassword: String
)