package com.jskaleel.vizhi_tamil.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.jskaleel.vizhi_tamil.BuildConfig
import com.jskaleel.vizhi_tamil.R
import com.jskaleel.vizhi_tamil.databinding.ActivitySettingsBinding
import com.jskaleel.vizhi_tamil.ui.contrib.ContributorsActivity
import com.jskaleel.vizhi_tamil.ui.oss.ThirdPartyActivity
import com.jskaleel.vizhi_tamil.utils.Constants
import com.jskaleel.vizhi_tamil.utils.openUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private val binding: ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.txtAppVersion.text =
            String.format(getString(R.string.version), BuildConfig.VERSION_NAME)

        binding.txtKaniyamDesc.text = HtmlCompat.fromHtml(
            getString(R.string.kaniyam_foundation_desc),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.txtVglugDesc.text = HtmlCompat.fromHtml(
            getString(R.string.vglug_foundation_desc),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        binding.llPrivacyPolicy.setOnClickListener {
            openUrl(baseContext, Constants.PRIVACY_POLICY_URL)
        }

        binding.llTermsCondition.setOnClickListener {
            openUrl(baseContext, Constants.TERMS_CONDITIONS_URL)
        }

        binding.layoutKaniyam.setOnClickListener {
            openUrl(baseContext, "http://www.kaniyam.com/")
        }

        binding.layoutVglug.setOnClickListener {
            openUrl(baseContext, "https://vglug.org")
        }

        binding.rlSourceCodeLayout.setOnClickListener {
            openUrl(baseContext, "https://github.com/khaleeljageer/OCR-Tamil")
        }

        binding.rlContribLayout.setOnClickListener {
            startActivity(ContributorsActivity.newIntent(baseContext))
        }

        binding.rlOSSLayout.setOnClickListener {
            startActivity(ThirdPartyActivity.newIntent(baseContext))
        }

        binding.llShareApp.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    String.format(getString(R.string.share_text), Constants.STORE_URL)
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_app))
            startActivity(shareIntent)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}