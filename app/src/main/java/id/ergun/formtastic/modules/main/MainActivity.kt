package id.ergun.formtastic.modules.main

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.RadioButton
import android.widget.RadioGroup
import id.ergun.formtastic.R
import id.ergun.formtastic.models.User
import id.ergun.formtastic.utils.fromHtml
import id.ergun.formtastic.utils.invisible
import id.ergun.formtastic.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.ctx
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity: AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        presenter = MainPresenter(this)
        cb_kebijakan.setOnCheckedChangeListener { buttonView, isChecked ->
            btn_daftar.isEnabled = buttonView.isChecked

            setButtonBg()
        }

        btn_daftar.setOnClickListener {
            val user = User(
                    namaDepan = et_nama_depan.text.toString(),
                    namaBelakang = et_nama_belakang.text.toString(),
                    email = et_email.text.toString(),
                    jenisKelamin = et_jenis_kelamin.text.toString(),
                    password = et_password.text.toString(),
                    cpassword = et_cpassword.text.toString()
            )
            presenter.validate(user)
        }

        et_jenis_kelamin.onClick {
            dialogJenisKelamin()
        }
    }

    private fun initView() {
        btn_daftar.isEnabled = cb_kebijakan.isChecked
        setButtonBg()

        et_jenis_kelamin.setText("Laki-laki")

        progressbar.invisible()
    }

    // dialog jenis kelamin
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private lateinit var dialog: Dialog

    var JENIS_KELAMIN = 1 // default
    var LAKI_LAKI = 1
    var PEREMPUAN = 2

    private fun dialogJenisKelamin() {
        dialog = Dialog(ctx)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_jenis_kelamin)

        val rg_jenis_kelamin: RadioGroup = dialog.findViewById(R.id.rg_jenis_kelamin)
        val rb_laki: RadioButton = dialog.findViewById(R.id.rb_laki)
        val rb_perempuan: RadioButton = dialog.findViewById(R.id.rb_perempuan)

        if (JENIS_KELAMIN==LAKI_LAKI) rb_laki.isChecked = true
        else rb_perempuan.isChecked = true

        rg_jenis_kelamin.setOnCheckedChangeListener { group, checkedId ->
            if (rb_laki.isChecked) {
                JENIS_KELAMIN = LAKI_LAKI
                et_jenis_kelamin.setText("Laki-laki")
            }
            if (rb_perempuan.isChecked) {
                JENIS_KELAMIN = PEREMPUAN
                et_jenis_kelamin.setText("Perempuan")
            }

            dialog.dismiss()
        }
        dialog.show()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun setButtonBg() {
        if (btn_daftar.isEnabled) btn_daftar.backgroundResource = R.drawable.btn
        else btn_daftar.backgroundResource = R.drawable.btn_bg_disabled
    }

    override fun showLoading() {
        progressbar.visible()
    }

    override fun hideLoading() {
        progressbar.invisible()
    }

    override fun showResult(result: String) {
        toast(result)
    }

    override fun showError(error: String) {
        alert {
            title = "Pesan Error"
            message = error.fromHtml()
            positiveButton("Tutup") {}
        }.show()
    }
}