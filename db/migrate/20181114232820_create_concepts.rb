class CreateConcepts < ActiveRecord::Migration
  def change
    create_table :concepts do |t|
      t.string :name
      t.string :url

      t.timestamps null: false
    end
  end
end
