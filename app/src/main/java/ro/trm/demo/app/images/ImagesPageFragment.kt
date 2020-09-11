package ro.trm.demo.app.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gxorg.god.tools.network.Status
import kotlinx.android.synthetic.main.fragment_page_images.*
import ro.trm.demo.R
import ro.trm.demo.app.models.Image
import ro.trm.demo.tools.RecyclerBaseAdapter
import ro.trm.demo.tools.setupRecyclerView

class ImagesPageFragment : Fragment() {

    private var adapter: RecyclerBaseAdapter<Image, ImageListItemView>? = null
    private val viewModel : ImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_page_images, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let{
            adapter = setupRecyclerView(it, images, ImageListItemView::class.java)
        }
        viewModel.getImages().observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Status.ERROR -> {
                    showLoading(false)
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    adapter?.addObjectList(resource.data)
                }
            }
        })
    }

    fun showLoading(isLoading: Boolean = true) {
    }
}
