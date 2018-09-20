package tina.com.goods.presenter

import com.tina.base.ext.execute
import com.tina.base.presenter.BasePresenter
import com.tina.base.rx.BaseSubscribler
import tina.com.goods.data.protocol.Category
import tina.com.goods.presenter.view.CategoryView
import tina.com.goods.service.CategoryService
import javax.inject.Inject

/**
 * @author yxc
 * @date 2018/9/15
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var userService: CategoryService

    fun getCategory(parentId: Int) {
        /*
            业务逻辑
         */
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.getCategory(parentId)
                .execute(object : BaseSubscribler<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                        mView.onGetCategoryResult(t)
                    }
                }, lifecycleProvider)
    }

}