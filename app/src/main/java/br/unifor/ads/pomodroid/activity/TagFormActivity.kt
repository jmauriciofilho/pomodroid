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

class TagFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mNameTag: EditText
    private lateinit var mSalvarBtn: Button

    private lateinit var tagDAO: TagDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_form)

        mNameTag = findViewById(R.id.nome_tag)

        mSalvarBtn = findViewById(R.id.salvar_btn_tag)
        mSalvarBtn.setOnClickListener(this)

        tagDAO = TagDAO(this)
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.salvar_btn_tag -> {
                val name = mNameTag.text.toString()
                val tag = Tag(null, name, 1)
                tagDAO.insert(tag)

                val it = Intent(this, MainActivity::class.java)
                startActivity(it)

                finish()
            }

        }
    }
}
