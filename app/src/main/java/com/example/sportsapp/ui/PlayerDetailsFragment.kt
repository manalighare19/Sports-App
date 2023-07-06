package com.example.sportsapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sportsapp.R
import com.example.sportsapp.databinding.FragmentPlayerDetailsBinding

class PlayerDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPlayerDetailsBinding
    private val args: PlayerDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.player_details_fragment_title)
        // Inflate the layout for this fragment
        binding =  FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            playerName.text = args.player.strPlayer

            val imageUrl = args.player.strThumb
            Glide.with(playerImage.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_account)
                .into(playerImage)

            playerDescription.text = args.player.strDescriptionEN
            playerPosition.text = args.player.strPosition

            val instagramLink: String? = args.player.strInstagram
            if (instagramLink.isNullOrBlank()) {
                // Instagram link is null, hide or disable the button
                instagramBtn.visibility = View.GONE
            } else {
                // Set the OnClickListener to launch the Instagram link
                instagramBtn.setOnClickListener {
                    val uri =
                        if (instagramLink.startsWith("http://") || instagramLink.startsWith("https://")) {
                            Uri.parse(instagramLink)
                        } else {
                            Uri.parse("https://$instagramLink")
                        }
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }

            val twitterLink: String? = args.player.strTwitter
            if (twitterLink.isNullOrBlank()) {
                // Twitter link is null, hide or disable the button
                twitterBtn.visibility = View.GONE
            } else {
                // Set the OnClickListener to launch the Twitter link
                twitterBtn.setOnClickListener {
                    val uri =
                        if (twitterLink.startsWith("http://") || twitterLink.startsWith("https://")) {
                            Uri.parse(twitterLink)
                        } else {
                            Uri.parse("https://$twitterLink")
                        }
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
        }
    }
}