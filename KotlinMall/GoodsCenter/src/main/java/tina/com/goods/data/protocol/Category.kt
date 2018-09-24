package tina.com.goods.data.protocol

/**
 * @author yxc
 * @date 2018/9/20
 * 商品分类
 */
data class Category(
        val id: Int, //分类ID
        val categoryName: String, //分类名称
        val categoryIcon: String = "", //分类图标
        val parentId: Int, //分类 父级ID
        var isSelected: Boolean = false//是否被选中
)