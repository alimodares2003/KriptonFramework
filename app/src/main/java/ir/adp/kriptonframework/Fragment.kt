package ir.adp.kriptonframework

import android.os.Bundle
import android.view.View
import ir.adp.framework.base.BaseFormFragment
import ir.adp.framework.utils.listener.IApiListener
import ir.adp.framework.utils.toastL
import kotlinx.android.synthetic.main.activity_form.*
import retrofit2.Response

class Fragment : BaseFormFragment() {

    init {
        layout = R.layout.fragment_form
        dialogText = "درحال ارسال ..."
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fv.errorText = "نیاز است"
        fv.setOnSubmitClickListener {
            runApi(context!!, getService<MainService>().sendData(MainRequest("cv")), this)
        }
    }

    override fun onSuccessApi(rs: Response<*>, listener: IApiListener) {
        toastL("succesful")
    }
}