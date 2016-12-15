/**
 * Function:Product
 * Date:2016.12.11
 * Author:LiuXin
 */
package model;

import com.jfinal.plugin.activerecord.Model;

public class Product extends Model<Product> {
	private static final long serialVersionUID = 1L;
	public static final Product dao = new Product();	
}