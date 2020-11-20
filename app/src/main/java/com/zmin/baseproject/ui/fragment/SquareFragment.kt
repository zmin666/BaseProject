package com.zmin.baseproject.ui.fragment

import android.R.attr.apiKey
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.zmin.baseproject.R
import com.zmin.baseproject.data.request.GetBodyRequest
import com.zmin.baseproject.databinding.FragmentSquareBinding
import com.zmin.baseproject.viewmodel.request.RequesBodyViewModel
import com.zmin.mvvm.base.fragment.BaseFragment
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File


/**
 * @author: ZhangMin
 * @date: 2020/11/12 14:31
 * @version: 1.0
 * @desc:
 */
@Suppress("SENSELESS_COMPARISON")
class SquareFragment : BaseFragment<BaseViewModel, FragmentSquareBinding>() {
    override fun layoutId(): Int = R.layout.fragment_square

    //请求数据ViewModel
    private val requestHomeViewModel: RequesBodyViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        mDataBinding.proxy = Proxy()
    }

    override fun createObserver() {
        super.createObserver()
        requestHomeViewModel.run {
            bodyDataState.observe(viewLifecycleOwner, Observer {
                if (it.skeletons != null) {
                    ToastUtils.showShort("数据来了")
                } else {
                    ToastUtils.showShort("出错")
                }
            })
        }
    }

    inner class Proxy {

        fun clickText() {
            val api_key = "_CNUM8HOAvVV2r-VThyBrBjI_yUM2Rvm"
            val api_secret = "JLDuJlntW71CpNniMyTmlz8z8TS-eOtQ"
            val image_url =
                "http://cmsresources.oss-cn-chengdu.aliyuncs.com/3f07a6631ae160a97f9f166995708976.png?Expires=1911439655&OSSAccessKeyId=LTAI4FhmNmLU4kicPay79v8v&Signature=hXIfruFLWJxkdGTFXoDC%2BdbKQPw%3D"
            val image_file: File? = null
            val image_base64 = ""
//            val getBodyRequest =
//                GetBodyRequest(api_key, api_secret, image_url, image_file, image_base64)

            val requestApiKey: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), api_key)
            val requestApiSecret: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), api_secret)
            val requestImageUrl: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), image_url)
            val requestBodyMap= mutableMapOf<String, RequestBody>()
            requestBodyMap["api_key"] = requestApiKey
            requestBodyMap["api_secret"] = requestApiSecret
            requestBodyMap["image_url"] = requestImageUrl
            requestHomeViewModel.getBodyData(requestBodyMap)
        }
    }
}