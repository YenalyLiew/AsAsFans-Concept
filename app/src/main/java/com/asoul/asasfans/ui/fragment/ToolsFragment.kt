package com.asoul.asasfans.ui.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asoul.asasfans.AsApplication
import com.asoul.asasfans.R
import com.asoul.asasfans.databinding.FragmentToolsBinding
import com.asoul.asasfans.ui.activity.WebActivity
import com.asoul.asasfans.utils.addToolsItem
import com.asoul.asasfans.utils.showShortToast

/**
 * @ProjectName : AsAsFans
 * @Author : Yenaly Liew
 * @Time : 2022/04/06 006 20:11
 * @Description : Description...
 */
class ToolsFragment : Fragment() {

    companion object {
        private const val ASOUL_CNKI =
            "https://asoulcnki.asia"
        private const val DIALECT_DICT =
            "https://tools.asoulfan.com/zhijiangDict"
        private const val LITTLE_COMPOSITION =
            "https://asoul.icu/"
        private const val CHAT_RECORD_PUBLIC =
            "https://asoul.asia/"
        private const val ASOUL_WIKI =
            "https://asoulwiki.com/"
        private const val COMPOSITION_GENERATOR =
            "http://asoul.infedg.xyz/"
        private const val ART_AND_RECORD =
            "https://nf.asoul-rec.com"
        private const val STATUS_CHECK =
            "https://tools.asoulfan.com/ingredientChecking"
        private const val QA_SEARCH =
            "http://asoulfan.xyz/"
    }

    private var _binding: FragmentToolsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ToolsFragment", "Launched")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToolsBinding.inflate(inflater, container, false)
        initToolsItem()
        return binding.root
    }

    private fun initToolsItem() {
        val linearViewGroup = binding.toolsList
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_zwcc,
            text = R.string.zhi_net_check_duplicate,
            moreAction = { it, _, _, _ ->
                it.click(ASOUL_CNKI)
                it.longClick(ASOUL_CNKI)
            }
        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_zhijiang_book,
            text = R.string.dialect_dict,
            moreAction = { it, _, _, _ ->
                it.click(DIALECT_DICT)
                it.longClick(DIALECT_DICT)
            }

        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_long_comment,
            text = R.string.little_composition,
            moreAction = { it, _, _, _ ->
                it.click(LITTLE_COMPOSITION)
                it.longClick(LITTLE_COMPOSITION)
            }
        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_asoul,
            text = R.string.group_chat_history_public,
            moreAction = { it, _, _, _ ->
                it.click(CHAT_RECORD_PUBLIC)
                it.longClick(CHAT_RECORD_PUBLIC)
            }
        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_asoul,
            text = R.string.asoul_wiki,
            moreAction = { it, _, _, _ ->
                it.click(ASOUL_WIKI)
                it.longClick(ASOUL_WIKI)
            }
        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_asoul,
            text = R.string.composition_generator,
            moreAction = { it, _, _, _ ->
                it.click(COMPOSITION_GENERATOR)
                it.longClick(COMPOSITION_GENERATOR)
            }
        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_asoul,
            text = R.string.art_and_record_site,
            moreAction = { it, _, _, _ ->
                it.click(ART_AND_RECORD)
                it.longClick(ART_AND_RECORD)
            }
        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_asf_bak,
            text = R.string.status_search,
            moreAction = { it, _, _, _ ->
                it.click(STATUS_CHECK)
                it.longClick(STATUS_CHECK)
            }
        )
        addToolsItem(
            linearViewGroup,
            icon = R.drawable.icon_asf_bak,
            text = R.string.QA_search,
            moreAction = { it, _, _, _ ->
                it.click(QA_SEARCH)
                it.longClick(QA_SEARCH)
            }
        )
    }

    private fun View.click(url: String) {
        this.setOnClickListener {
            val intent = Intent(requireContext(), WebActivity::class.java).apply {
                putExtra("web_url", url)
            }
            startActivity(intent)
        }
    }

    private fun View.longClick(url: String) {
        this.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        this.setOnLongClickListener {
            val clipBoardManager =
                AsApplication.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipDataSet = ClipData.newPlainText(null, url)
            clipBoardManager.setPrimaryClip(clipDataSet)
            "地址复制成功！".showShortToast()
            // true代表事件分发到此结束，不会传递到短按事件。
            true
        }
    }
}