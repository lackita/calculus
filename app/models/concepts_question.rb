class ConceptsQuestion < ActiveRecord::Base
  belongs_to :concept
  belongs_to :question
end
