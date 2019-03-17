class AddEasinessFactorToReviews < ActiveRecord::Migration
  def change
    add_column :reviews, :easiness_factor, :float
  end
end
