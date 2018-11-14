class QuestionConcepts < ActiveRecord::Migration
  def change
    create_table :questions_concepts do |t|
      t.integer :question_id
      t.integer :concept_id

      t.timestamps null: false
    end
  end
end
