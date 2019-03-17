class AddCorrectToReviews < ActiveRecord::Migration
  def change
    add_column :reviews, :correct, :boolean
  end
end
