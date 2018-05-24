package br.unifor.ads.pomodroid.activity


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.unifor.ads.pomodroid.R
import br.unifor.ads.pomodroid.adapter.TagsAdapter
import br.unifor.ads.pomodroid.dao.TagDAO
import br.unifor.ads.pomodroid.entity.Tag


/**
 * A simple [Fragment] subclass.
 */
class TagsFragment : Fragment(),  View.OnClickListener  {

    private lateinit var mAddTagBtn: FloatingActionButton
    private lateinit var mTags: RecyclerView

    private lateinit var mRecyclerViewAdapter: RecyclerView.Adapter<*>
    private lateinit var mRecyclerViewLayoutManager: LinearLayoutManager

    private lateinit var tagDAO: TagDAO
    private lateinit var tags: MutableList<Tag>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val inf = inflater.inflate(R.layout.fragment_tags, container, false)

        tagDAO = TagDAO(activity as MainActivity)
        tags = tagDAO.findAll().toMutableList()

        mRecyclerViewAdapter = TagsAdapter(activity as MainActivity, tags)
        mRecyclerViewLayoutManager = LinearLayoutManager(activity as MainActivity)

        mTags = inf.findViewById<RecyclerView>(R.id.main_recycler_tags).apply {
            setHasFixedSize(false)
            layoutManager = mRecyclerViewLayoutManager
            adapter = mRecyclerViewAdapter
        }

        mAddTagBtn = inf.findViewById<FloatingActionButton>(R.id.main_recycler_tag_add)
        mAddTagBtn.setOnClickListener(this)

        return inf
    }

    override fun onStart() {
        super.onStart()
        tags.clear()
        tags.addAll(0, tagDAO.findAll())
        mTags.adapter.notifyDataSetChanged()
    }


    override fun onClick(v: View?) {
        val tagForm = Intent(activity as MainActivity, TagFormActivity::class.java)
        startActivity(tagForm)
    }

}
