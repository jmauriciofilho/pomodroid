package br.unifor.ads.pomodroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.dao.TagDAO
import br.unifor.ads.pomodroid.entity.Tag

class TestTagActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_tag)

        createTag()

    }


    private fun createTag(){
        val tagDAO = TagDAO(TestTagActivity@this)

        val tag = Tag(null, "Média prioridade", 1)
        val tag2 = Tag(null, "Baixa prioridade", 1)

        tagDAO.insert(tag)
        tagDAO.insert(tag2)

        val tags = tagDAO.findAll()

        Log.d("result", tags.toString())
    }

    private fun updateTag(){
        val tagDAO = TagDAO(TestTagActivity@this)

        val tagOld = tagDAO.find(2)

        Log.d("result", tagOld.toString())

        val task = Tag(2, "Prioridade Update", 1)

        tagDAO.update(task)

        val tagAlterada = tagDAO.find(2)

        Log.d("result", tagAlterada.toString())
    }

    private fun deleteTag(){
        val tagDAO = TagDAO(TestTaskListActivity@this)

        val tag = tagDAO.find(2)

        tagDAO.delete(tag!!)

        val tags = tagDAO.findAll()

        Log.d("result", tags.toString())
    }


}
