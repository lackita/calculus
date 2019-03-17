class AddNextReviewToConcepts < ActiveRecord::Migration
  def change
    add_column :concepts, :next_review, :datetime
  end
end
