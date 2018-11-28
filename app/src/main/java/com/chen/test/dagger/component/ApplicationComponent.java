package com.chen.test.dagger.component;

import android.app.Application;
import android.content.Context;

import com.chen.test.base.DbManager;
import com.chen.test.dagger.module.ApplicationModule;
import com.chen.test.dagger.module.NetModule;
import com.chen.test.dagger.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chenxianglin on 2018/5/15.
 * Class note:
 */

/**
 * 出处：https://blog.csdn.net/gao878280390/article/details/78844584
 * modules 指明Component查找Module的位置，
 * 必须定义为接口，Dagger2框架将自动生成Component的实现类，对应的类名是Dagger×××××
 * <p>
 * module,provide方法被指定为singleton，则component必须添加singleton的注释
 * <p>
 * 多Module的注入形式：
 *
 * @Component(modules={ModuleA.class,ModuleB.class,ModuleC.class})
 * @或
 * @Module(includes={ModuleA.class,ModuleB.class,ModuleC.class})
 * @Component(modules={MyModule.class})
 *
 * 也就是说一个没有scope的组件component不可以依赖一个有scope的组件component。
 * 子组件和父组件的scope不能相同。
 * 我们通常的ApplicationComponent都会使用Singleton注解，也就会是说我们如果自定义component必须有自己的scope。
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {
    /**
     * 注入到A(Container)的方法，方法名一般使用inject
     */
    void inject(Application application);

    /**
     * component的dependencies中，对外显示的提供依赖，供子Component使用
     *
     * @Subcomponent 不需要在父component中显式添加子component需要用到的对象，
     * 只需要添加返回子Component的方法即可，子Component能自动在父Component中查找缺失的依赖
     * 如：ActivityComponent activityComponent();
     * */

    /**
     * ApplicationContext 为ApplicationContext类型的Context
     * <p>
     * <p>
     * 如果返回参数相同可以使用@Named("name")
     * <p>
     * 或者
     *
     * @Qualifier
     * @Documented //起到文档提示作用
     * @Retention(RetentionPolicy.RUNTIME) //注解范围是Runtime级别
     * public @interface ClassName{
     * String value() default "defaultValue"
     * }
     */
    @ApplicationContext
    Context context();

    //对外显示的提供依赖，供子Component使用
    Application application();

    //对外显示的提供依赖，供子Component使用
    DbManager dbManager();
}
