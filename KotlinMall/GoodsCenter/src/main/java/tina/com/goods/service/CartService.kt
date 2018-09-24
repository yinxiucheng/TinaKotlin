package tina.com.goods.service

import rx.Observable
import tina.com.goods.data.protocol.CartGoods

/**
 * @author yxc
 * @date 2018/9/24
 */
interface CartService {
    /*
            添加商品到购物车
         */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

    /*
        获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>

    /*
        购物车结算
    */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int>


}