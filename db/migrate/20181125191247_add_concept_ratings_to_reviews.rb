class AddConceptRatingsToReviews < ActiveRecord::Migration
  def change
    add_column :reviews, :concept_ratings, :text
  end
end
