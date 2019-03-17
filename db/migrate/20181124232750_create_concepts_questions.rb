class CreateConceptsQuestions < ActiveRecord::Migration
  def change
    create_table :concepts_questions do |t|
      t.integer :question_id
      t.integer :concept_id

      t.timestamps null: false
    end
  end
end
