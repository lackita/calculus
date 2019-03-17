class AddVariablesToQuestions < ActiveRecord::Migration
  def change
    add_column :questions, :variables, :text
  end
end
