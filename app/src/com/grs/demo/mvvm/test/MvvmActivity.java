package com.grs.demo.mvvm.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.grs.demo.R;
import com.grs.demo.databinding.ActivityMvvmBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 1,Binding生成规则
 * 默认生成规则：xml通过文件名生成，使用下划线分割大小写。
 * 比如activity_demo.xml，则会生成ActivityDemoBinding，item_search_hotel则会生成ItemSearchHotelBinding。
 * view的生成规则类似，只是由于是类变量，首字母不是大写，比如有一个TextView的id是first_name，则会生成名为firstName的TextView。
 * 2,注意：
 * 只要是在Java中需要导入包的类，这边都需要导入，如：Map、ArrayList 等，不过 java.lang 包里的类是可以不用导包的。
 */
public class MvvmActivity extends AppCompatActivity {

	/**
	 * 4,调用Activity中变量 注意：这个变量必须是 public static。
	 */
	public static String mName = "MM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
		User user = new User();
		user.setFirstName("测试1");
		user.setLastName("测试2");
		user.setUrl("https://up.enterdesk.com/edpic_source/5d/a3/25/5da325c79e2b1fbb991c6b494259387d.jpg");
		binding.setUser(user);
		//点击事件
		binding.setHandle(new MyHandler());

		//这里 new 了一个 User2 对象后，直接就绑定了。之后只要 mUser2 中的数据发生变化，UI也会随之更新。
		User2 user2 = new User2();
		binding.setUser2(user2);
		user2.firstName.set("用户1");
		user2.lastName.set("用户2");
		user2.age.set(20);
		user2.isUser.set(true);
		user2.list.add("list1");
		user2.list.add("list2");
		user2.map.put("111", "项目1");
		user2.map.put("222", "项目2");

		//跟新数据
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				user.setFirstName("1111111111111111111111");
				user2.firstName.set("1111111111111111111111");
			}
		}, 3000);

		//模拟数据
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			User u = new User();
			u.setFirstName("测试1");
			u.setLastName("测试2");
			u.setUrl("https://up.enterdesk.com/edpic_source/5d/a3/25/5da325c79e2b1fbb991c6b494259387d.jpg");
			userList.add(u);
		}
		//不过，在自动生成的ActivityMainBinding 中，我们可以看到根据 RecyclerView的id，
		// 会自动生成一个 recyclerView。
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
		binding.recyclerView.setLayoutManager(linearLayoutManager);
		binding.recyclerView.setAdapter(new MyAdapter(userList));
	}
}
