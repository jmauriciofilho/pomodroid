package br.unifor.ads.pomodroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TagDAO
import br.unifor.ads.pomodroid.entity.Tag

class TagFormEditActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mNameTag: EditText
    private lateinit var mSalvarBtn: Button
    private lateinit var mExcluirBtn: Button

    private lateinit var tagDAO: TagDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_form_edit)

        mNameTag = findViewById(R.id.nome_tag_edit)

        mSalvarBtn = findViewById(R.id.salvar_btn_tag)
        mSalvarBtn.setOnClickListener(this)

        mExcluirBtn = findViewById(R.id.excluir_tag)
        mExcluirBtn.setOnClickListener(this)

        tagDAO = TagDAO(this)

        getTag()
    }

    fun getTag(){
        val id = intent.getIntExtra("idTag", 0)
        val tag = tagDAO.find(id)
        mNameTag.setText(tag!!.name)
    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.salvar_btn_tag -> {
                val id = intent.getIntExtra("idTag", 0)
                val name = mNameTag.text.toString()
                val tag = Tag(id, name, 1)
                tagDAO.update(tag)

                val it = Intent(this, MainActivity::class.java)
                startActivity(it)

                finish()
            }

            R.id.excluir_tag -> {
                val id = intent.getIntExtra("idTag", 0)
                val name = mNameTag.text.toString()
                val tag = Tag(id, name, 1)
                tagDAO.delete(tag)

                val it = Intent(this, MainActivity::class.java)
                startActivity(it)

                finish()
            }

        }
    }
}
