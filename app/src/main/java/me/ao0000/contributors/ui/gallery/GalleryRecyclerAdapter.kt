package me.ao0000.contributors.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import me.ao0000.contributors.databinding.ContributorItemBinding
import me.ao0000.contributors.model.Contributor

class GalleryRecyclerAdapter(private val onClick: (String) -> Unit) :
    ListAdapter<Contributor, GalleryRecyclerAdapter.ContributorViewHolder>(
        DiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder =
        ContributorViewHolder.create(
            parent
        )

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClick)
    }

    class ContributorViewHolder(
        private val binding: ContributorItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            contributor: Contributor,
            onClick: (String) -> Unit
        ) {
            binding.userAvatarImage.load(contributor.avatarUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.contributor = contributor
            binding.contributionsText.text = "Contributions : " + contributor.contributions
            binding.root.setOnClickListener {
                onClick(contributor.name)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup): ContributorViewHolder {
                val binding = ContributorItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ContributorViewHolder(
                    binding
                )
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Contributor>() {
    override fun areItemsTheSame(oldItem: Contributor, newItem: Contributor): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Contributor, newItem: Contributor): Boolean =
        oldItem == newItem
}