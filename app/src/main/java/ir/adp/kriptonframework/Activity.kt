package ir.adp.kriptonframework

import android.os.Bundle
import ir.adp.framework.base.BaseFormActivity
import ir.adp.framework.utils.listener.IApiListener
import ir.adp.framework.utils.toastL
import kotlinx.android.synthetic.main.activity_form.*
import retrofit2.Response

class Activity : BaseFormActivity(), IApiListener {

    init {
        isShowBack = true
        dialogText = "در حال ارسال..."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setToolbarTitle(R.string.app_name)

        fv.errorText = "نیاز است"
        fv.setOnSubmitClickListener {
            runApi(this, getService<MainService>().sendData(MainRequest("cv")), this)
        }


    }

    override fun onSuccessApi(rs: Response<*>, listener: IApiListener) {
        toastL("succesful")
    }
}