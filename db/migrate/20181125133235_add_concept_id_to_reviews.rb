class AddConceptIdToReviews < ActiveRecord::Migration
  def change
    add_column :reviews, :concept_id, :integer
  end
end
